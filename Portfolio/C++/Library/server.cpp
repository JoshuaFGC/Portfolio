#include <iostream>
#include <string>
#include <vector>
#include <curl/curl.h>
#include <sstream>
#include <fstream>

#include "httplib.h"
#include "ApiRequest.h"
#include "json.hpp"
#include "Sinonimo.h"
#include "ProcesamientoLibro.h"
#include "BPlus.h"
#include "avl.h"
#include "LibroResultante.h"



using json = nlohmann::json;
using namespace httplib;
using namespace std;

size_t WriteCallback(void *contents, size_t size, size_t nmemb, string *output) {
    
    size_t total_size = size * nmemb;
    output->append(static_cast<char*>(contents), total_size);
    return total_size;
}

int main() {
    int apariciones = 0;
    int tipo = 0;
    vector<string> feelingsPhrase;
    vector<string> titulosLibro;
    avl<string, vector<string>> avlFeeling;//Para buscar los mejores libros
    BPlus BPlusParrafo(2);//Para buscar en los parrafos de los libros
    ProcesamientoLibro procesar(BPlusParrafo);
    ApiRequest api;


    //Se obtienen todos los titulos
    ifstream libros("libros.txt");
    string titulo;
    while (getline(libros, titulo)) {
        titulosLibro.push_back(titulo);
    }
    libros.close();

    //Se obtienen los sentimientos de cada libro y se insertan en el avl
    for (const string &name : titulosLibro){
        
        string bookTitle = name; 
        vector<string> feelingsBook = api.getFeelings(name, tipo=2);

        avlFeeling.insert(name, feelingsBook, apariciones);
    }

    


    Server svr;
    


    svr.Post("/Obtener_libros", [&avlFeeling, &api, &BPlusParrafo, &tipo, &procesar](const Request& req, Response& res) {
    string frase = req.body;
    istringstream stream(frase);
    string palabra;
    unordered_map<string, vector<string>> sinonimosMap;
    
    vector<string> feelingsPhrase = api.getFeelings(frase, tipo = 1);
    string sentimientoCentral = feelingsPhrase[0];

    //Eliminar repetidas y articulos o conectores
    while (stream >> palabra) {
        
        transform(palabra.begin(), palabra.end(), palabra.begin(), ::tolower);

        //Obtener sinónimos para esta palabra
        Sinonimo sinonimos = api.ObtenerSinonimos(palabra);
        
        for (const auto& entry : sinonimos.synonyms) {
            sinonimosMap[entry.first] = entry.second;
        }
    }

    
    //Se obtienen los libros que hagan match con los sentimientos de la frase
// Suponiendo que librosSeleccionados es el vector de pares <string, int>
    vector<pair<string, int>> librosSeleccionados = avlFeeling.similitud(&avlFeeling, feelingsPhrase);


    //Ordenar de mayor a menor los libros
    sort(librosSeleccionados.begin(), librosSeleccionados.end(), [](const auto& a, const auto& b){
        return a.second < b.second;
    });

    //Se carga un B+ con los nombres de los libros y sus parrafos
    //Solo los libros con match
    BPlusParrafo = procesar.cargarBPlusParrafos(librosSeleccionados);

    vector<LibroResultante> librosFinales = BPlusParrafo.obtenerLibroMasCoincidente(sinonimosMap, BPlusParrafo);
    
    


    json respuesta = json::array();
    respuesta["Sentimiento de la frase"] = sentimientoCentral; 
    for (const auto& libro: librosFinales){
        json libroJson;
        libroJson["Titulo"] = libro.titulo;
        libroJson["Autor"] = libro.autor;
        
        json parrafosJson = json::array();
        for (const auto& parrafo : libro.parrafos){
            json parrafoJson;
            for (const auto& linea : parrafo){
                parrafoJson.push_back(linea);
            }
            parrafosJson.push_back(parrafoJson);
        }
        libroJson["Parrafos"] = parrafosJson;

        respuesta.push_back(libroJson);
    }

    res.set_content(respuesta.dump(), "application/json");
    });


    //Esto ayuda a conectar mi server con el html
    svr.Get("/", [](const Request&, Response& res) {
        ifstream file("requestHtml.html");

        if (file){
            ostringstream oss;
            oss << file.rdbuf();
            file.close();

            res.set_content(oss.str(), "text/html");
        }else{
            res.status = 404;
            res.set_content("No matches", "text/html");
        }
    });

    svr.listen("0.0.0.0", 8080);

    return 0;
}
