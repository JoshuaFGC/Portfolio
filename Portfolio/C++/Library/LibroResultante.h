#ifndef _LibroResultante_
#define _LibroResultante_

#include <iostream>
#include <vector>

using namespace std;

struct LibroResultante {
    string titulo;
    string autor;
    vector<vector<string>> parrafos;
};

#endif