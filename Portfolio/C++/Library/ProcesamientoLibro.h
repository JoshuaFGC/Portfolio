#ifndef _ProcesamientoLibro_
#define _ProcesamientoLibro_

#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <vector>
#include <filesystem>
#include <algorithm>
#include <unordered_set>
#include <thread>

#include "BPlus.h"
#include "Libro.h"
namespace fs = std::filesystem;

class ProcesamientoLibro{
    private:
        vector<string> conectores;
        vector<vector<string>> parrafos;
        Libro libro;
        BPlus pBPlus;

    public:
        ProcesamientoLibro(const BPlus& bPlusParrafo) : pBPlus(bPlusParrafo) {}
        
        bool esConector(const string& pPalabra){
            static const unordered_set<string> conectores = {"and", "but", "or", "so", "however"};
            return conectores.find(pPalabra) != conectores.end();
        }

        vector<vector<string>> obtenerParrafos(const string& pContenido){

        
            stringstream ss(pContenido);
            string linea;
            
            while (getline(ss, linea)){
                if (linea.empty()){
                    continue;
                }

                istringstream palabraStream(linea);
                string palabra;
                vector<string> parrafoAct;

                while (palabraStream >> palabra){
                    if (!esConector(palabra)) {
                        parrafoAct.push_back(palabra);
                    }
                }
                if (parrafoAct.size() >= 20){
                    parrafos.push_back(parrafoAct);
                }
            }
            return parrafos;  
        }


    Libro procesarLibro(const string& ruta){
        //libro.titulo = ruta;
        fstream archivo(ruta);

        if (!archivo.is_open()){
            return libro;
        }

        stringstream contenidoStream;
        contenidoStream << archivo.rdbuf();
        string contenido = contenidoStream.str();

        libro.parrafos = obtenerParrafos(contenido);

        return libro;
    }

    BPlus cargarBPlusParrafos(const vector<pair<string, int>>& pLibrosSeleccionados){


            // Vector para almacenar solo los nombres de los libros
        vector<string> librosSeleccionados;

    // Transformar el vector de pares a un vector de strings (solo los nombres de los libros)
        transform(pLibrosSeleccionados.begin(), pLibrosSeleccionados.end(), back_inserter(librosSeleccionados),
            [](const pair<string, int>& libro) { return libro.first; });


        fs::path folder = "../biblioteca";
        
        if (fs::is_directory(folder)) {
            for (const auto &file : fs::directory_iterator(folder)){
                if (file.is_regular_file() && file.path().extension() == ".txt") {
                    string rutaLibro = file.path().string();
                    
                    string nombre = file.path().stem().string();

                    auto validacion = find(librosSeleccionados.begin(), librosSeleccionados.end(), nombre);

                    if (validacion != librosSeleccionados.end()){
                        Libro lib = procesarLibro(rutaLibro);
                        pBPlus.insertar(lib.titulo, lib);
                    }
                }
            }
        }
        return pBPlus;
    }


};

#endif