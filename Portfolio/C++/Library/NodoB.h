#ifndef _NodoB_
#define _NodoB_

#include <vector>
#include <iostream>
#include "Libro.h"

using namespace std;
struct NodoB{
    public:
        const int max = 3;
        vector<string> keys;
        vector<NodoB*> hijo;
        vector<Libro> libros;
        bool leaf;
        int claves;

        NodoB(){
            claves = 0;
            leaf = true;
            keys.resize(max, "");
            hijo.resize(max+1, nullptr);
            libros.resize(max, Libro());
        }
};
#endif