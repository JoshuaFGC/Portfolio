

#include <iostream>
#include <string>
#include <vector>
#include <curl/curl.h>
#include <sstream>
#include <fstream>
#include <unordered_map>

#include "BPlus.h"
#include "avl.h"
#include "ApiRequest.h"
#include "ProcesamientoLibro.h"
#include "Sinonimo.h"

using namespace std;

int main() {
    BPlus bPlus(2);
    
    


    
    Libro libro1 = {"Libro 1", {{"pedro", "maria", "esteban"}, {"carro", "avion"}}};
    Libro libro2 = {"Libro 2", {{"Parrafo 1", "pascal"}, {"Holograma", "cama"}}};
    Libro libro3 = {"Libro 3", {{"loco", "compu"}, {"cafe", "tostada"}}};

    // Insertar libros en el árbol B+
    bPlus.insertar("Libro 1", libro1);
    bPlus.insertar("Libro 2", libro2);
    bPlus.insertar("Libro 3", libro3);

    // Definir el mapa de sinónimos
    unordered_map<string, vector<string>> sinonimosMap;
    sinonimosMap["pedro"] = {"maria", "cama", "Holograma", "a"};
    sinonimosMap["loco"] = {"cafe", "compu", "loco"};
    
    // Realizar la búsqueda en los libros
pair<string, unordered_map<string, vector<int>>> libroCoincidente = bPlus.obtenerLibroMasCoincidente(sinonimosMap, bPlus);

// Imprimir resultados con números de párrafo
if (!libroCoincidente.first.empty()) {
        cout << "Libro con más coincidencias: " << libroCoincidente.first << endl;
        // Haz lo que necesites con el libro encontrado
        // Accede a los datos de las coincidencias usando libroCoincidente.second
    } else {
        cout << "No se encontraron libros con coincidencias." << endl;
    }


    return 0;
}



// docker run -it --rm -v /c/Users/Lenovo/Documents/estruct2:/home gcc bash 
// apt update -y
// apt install curl   