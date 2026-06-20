#ifndef _BPlus_
#define _BPlus_

#include <vector>
#include <iostream>
#include <set>
#include <unordered_map>

#include "NodoB.h"
#include "LibroResultante.h"
#include "ApiRequest.h"
using namespace std;

class BPlus {
private:
    NodoB* root;
    int gMinimo; //grado minimo
    ApiRequest api;

    NodoB* getRoot(){
        return root;
    }
    
    
    NodoB* search(NodoB* actual, const string& key) {
        int i = 0;

        while (i < actual->claves && key > actual->keys[i]) {
            i++;
        }

        if (i < actual->claves && key == actual->keys[i]) {
            return actual;
        }

        if (actual->leaf) {
            return nullptr;
        }
        else {
            return search(actual->hijo[i], key);
        }
    }
//Nodo con puntero a un hijo, id del hijo, nodo original
    void split(NodoB* nuevoN, int i, NodoB* nodoOriginal) {
        NodoB* nTemporal = new NodoB();
        nTemporal->leaf = nodoOriginal->leaf;
        nTemporal->claves = gMinimo - 1;

        //Se copian las claves del original al resultante
        //Desde la mitad en adelante
        for (int j = 0; j < (gMinimo - 1); j++) {
            nTemporal->keys[j] = nodoOriginal->keys[j + gMinimo];
        }


        //Si el original es intermedio se copian los hijos al resultante
        if (!nodoOriginal->leaf) {
            for (int k = 0; k < gMinimo; k++) {
                nTemporal->hijo[k] = nodoOriginal->hijo[k + gMinimo];
            }
        }

        //Se divide tambien la cantidad de claves del original
        nodoOriginal->claves = gMinimo - 1;

        //Ajuste de punteros para nuevoN para hacer espacio
        for (int j = nuevoN->claves; j > i; j--) {
            nuevoN->hijo[j + 1] = nuevoN->hijo[j];
        }


        nuevoN->hijo[i + 1] = nTemporal;

        //Ajuste de claves para los hijos de nuevoN
        for (int j = nuevoN->claves - 1; j >= i; j--) {
            nuevoN->keys[j + 1] = nuevoN->keys[j];
        }

        //Se sube el valor del medio al nuevoN
        nuevoN->keys[i] = nodoOriginal->keys[gMinimo - 1];
        nuevoN->claves++;
    }


    //donde se va a insertar/ la llave que se va a insertar/ el libro a insertar
    void nonFullInsert(NodoB* nuevoN, const string& key, const Libro& libro) {

        
 
        if (nuevoN->leaf) {//verificacion para saber si es un nodo hoja

            int i = nuevoN->claves;

            if (nuevoN->claves == 0) {
                
                // Si el nodo está vacío inserta la clave y el libro en la posición 0
                nuevoN->keys[0] = key;
                
                nuevoN->libros[0] = libro;
                nuevoN->claves++;
                
                return;
            }

            //Se recorren las claves y desplazar
            while (i >= 1 && key < nuevoN->keys[i - 1]) {
                nuevoN->keys[i] = nuevoN->keys[i - 1];
                nuevoN->libros[i] = nuevoN->libros[i - 1];
                i--;
            }

            nuevoN->keys[i] = key;
            nuevoN->libros[i] = libro;
            nuevoN->claves++;

        } else {

            int cont2 = 0;
            while (cont2 < nuevoN->claves && key > nuevoN->keys[cont2]) {
                cont2++;//Para obtener la posicion correcta del hijo
            }

            //verificacion de llenado
            if (nuevoN->hijo[cont2]->claves == (2 * gMinimo - 1)) {
                split(nuevoN, cont2, nuevoN->hijo[cont2]);
                
                //Verificacion de desplazamiento
                if (key > nuevoN->keys[cont2]) {
                    cont2++;
                }
            }

            nonFullInsert(nuevoN->hijo[cont2], key, libro);
        }
    }

    NodoB* getNextNode(NodoB* actual){
        if (actual == nullptr){
            return nullptr;
        }

        if (!actual->leaf){
            return getNextNode(actual->hijo[0]);
        }

        if (actual->claves < actual->max - 1){
            return actual;
        }

        return nullptr;

    }

public:
    BPlus(int min) {
        this->root = new NodoB();
        this->gMinimo = min;
    }



    vector<vector<string>> buscarNodoPorClave(const string& key) {
        NodoB* nodo = search(root, key);
        vector<vector<string>> parrafos;

        if (nodo == nullptr) {
            std::cout << "No se ha encontrado un nodo con el valor ingresado" << std::endl;
            return {};
        }
        else {
            int i = 0;
            while (i < nodo->claves && key > nodo->keys[i]){
                i++;
            }
            
            if (i <  nodo->claves && key == nodo->keys[i]){
                for (const auto& parrafo : nodo->libros[i].parrafos){
                    parrafos.push_back(parrafo);
                }
            }
        }
        return parrafos;
    }


    //recibe el titulo y el struct libro
    void insertar(const string& key, const Libro& libro) {
        NodoB* raiz = root; //Inicializa la raiz como la raiz del B
        
        if (raiz->claves == (2 * gMinimo - 1)) {//Revisa si la raiz ya alcanzo el maximo de claves
            
            NodoB* nuevoN = new NodoB();
            
            root = nuevoN;
            
            nuevoN->leaf = false;
            nuevoN->claves = 0;
            
            nuevoN->hijo[0] = raiz;
            
            split(nuevoN, 0, raiz);
            nonFullInsert(nuevoN, key, libro);
        }
        else {
            
            nonFullInsert(raiz, key, libro);
        }
    }

    //Buscar matches con los parrafos de libros
vector<LibroResultante> obtenerLibroMasCoincidente(const unordered_map<string, vector<string>>& sinonimosMap, BPlus& bplus) {
    LibroResultante libroMasCoincidente;
    vector<LibroResultante> librosResu;
    int maxCoincidencias = 0;
    vector<vector<string>> parrafos;
    NodoB* nodoActual = bplus.getRoot();
    
    while (nodoActual != nullptr) {
        for (const auto& libro : nodoActual->libros) {
            unordered_map<string, vector<int>> contadorPalabras;
            
            int coincidenciasLibro = 0; // Variable para contar las coincidencias en este libro

            for (size_t i = 0; i < libro.parrafos.size(); ++i) {
                const auto& parrafo = libro.parrafos[i];
                vector<int> numerosParrafo; // Números de párrafo para la palabra actual

                for (const auto& palabraParrafo : parrafo) {
                    auto it = sinonimosMap.find(palabraParrafo);
                    if (it != sinonimosMap.end()) {
                        // La palabra del párrafo está presente en los sinónimos
                        numerosParrafo.push_back(i + 1); // Agregar número de párrafo (empezando desde 1)
                        parrafos.push_back(parrafo);
                        ++coincidenciasLibro; // Aumentar el contador de coincidencias
                    }
                }

                // Insertar números de párrafo para la palabra actual en el contador
                if (!numerosParrafo.empty()) {
                    contadorPalabras[libro.titulo].insert(contadorPalabras[libro.titulo].end(), numerosParrafo.begin(), numerosParrafo.end());
                }
            }

            // Si este libro tiene más coincidencias que el máximo actual, actualiza el máximo y el libro correspondiente
            if (coincidenciasLibro > maxCoincidencias) {
                maxCoincidencias = coincidenciasLibro;
                int tipo = 3;
                //libroMasCoincidente.autor = to_string (api.getFeelings(libro.titulo, tipo));
                libroMasCoincidente.titulo = libro.titulo;

                if(libroMasCoincidente.parrafos.size() < 3){
                    break;
                }

                libroMasCoincidente.parrafos = parrafos;
                librosResu.push_back(libroMasCoincidente);
            }

            if (librosResu.size() == 10){
                break;
            }
        }

        nodoActual = getNextNode(nodoActual); 
    }

    return librosResu;
}



};

#endif
