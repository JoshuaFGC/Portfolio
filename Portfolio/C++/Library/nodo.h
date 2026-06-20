#ifndef nodo_
#define nodo_

#include <set>
#include <unordered_map>
#include <string>
#include <iostream>
#include <vector>

using namespace std;
template <typename Key, typename Value>
struct nodo {
    Key key; //Siempre sera el nombre del libro
    Value value;//Es o los sentimientos del libro o los parrafos del libro
    int matches;
    int altura;
    nodo* left;
    nodo* right;
    

    nodo(const Key& n, const Value& s)
    : key(n), value(s), matches(0), left(nullptr), right(nullptr), altura(1){}
};

#endif