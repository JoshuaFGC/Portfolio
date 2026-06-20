#ifndef _avl_
#define _avl_

#include <iostream>
#include <vector>
#include <algorithm>
#include "nodo.h"

using namespace std;
template <typename Key, typename Value>

class avl {
private:
    
    nodo<Key, Value>* root;

    int altura(nodo<Key, Value>* nodo) {
        if (nodo == nullptr) {
            return 0;
        }
        return nodo->altura;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

     nodo<Key, Value>* rotateRight(nodo<Key, Value>* y) {
        nodo<Key, Value>* x = y->left;
        nodo<Key, Value>*T2 = x->right;

        x->right = y;
        y->left = T2;

        y->altura = max(altura(y->left), altura(y->right)) + 1;
        x->altura = max(altura(x->left), altura(x->right)) + 1;

        return x;
    }

    nodo<Key, Value>* rotateLeft(nodo<Key, Value>* x) {
        nodo<Key, Value>* y = x->right;
        nodo<Key, Value>* T2 = y->left;

        y->left = x;
        x->right = T2;

        x->altura = max(altura(x->left), altura(x->right) + 1);
        y->altura = max(altura(y->left), altura(y->right) + 1);

        return y;
    }

    int getBalance(nodo<Key, Value>* nodo) {
        if (nodo == nullptr) {
            return 0;
        }
        return altura(nodo->left) - altura(nodo->right);
    }

    nodo<Key, Value>* insert(nodo<Key, Value>* current, const Key& titulo, const Value& pSentimientos, int apariciones) {
        if (current == nullptr) {
            current = new nodo<Key, Value>(titulo, pSentimientos); // Crear un nuevo nodo 
            current->matches = apariciones;
            //current->book[titulo] = sentimientos; 
            return current;
        }

        if (titulo < current->key) {
            current->left = insert(current->left, titulo, pSentimientos, apariciones);
        } else if (titulo > current->key) {
            current->right = insert(current->right, titulo, pSentimientos, apariciones);
        } else {
            
            current->value = pSentimientos;
            
        }
        
        current->altura = 1 + max(altura(current->left), altura(current->right));

        int balance = getBalance(current);

        if (balance > 1 && titulo < current->left->key) {
            return rotateRight(current);
        }
        if (balance < -1 && titulo > current->right->key) {
            return rotateLeft(current);
        }
        if (balance > 1 && titulo > current->left->key) {
            current->left = rotateLeft(current->left);
            return rotateRight(current);
        }
        if (balance < -1 && titulo < current->right->key) {
            current->right = rotateRight(current->right);
            return rotateLeft(current);
        }

        return current;
    }



    void mostrar(nodo<Key, Value>* nodo) {
        if (nodo != nullptr) {
            mostrar(nodo->left);
            cout << "Titulo: " << nodo->key << "\n";
            for (const auto& value : nodo->value) {
                cout << "Sentimiento: " << value << "\n";
            }
            mostrar(nodo->right);
        }
    }

    nodo<Key, Value>* buscar(nodo<Key, Value>* nodo, const Key& titulo) {
        if (nodo == nullptr || nodo->key == titulo) {
            return nodo;
        }
        if (titulo < nodo->book.begin()->first) {
            return buscar(nodo->left, titulo);
        } else {
            return buscar(nodo->right, titulo);
        }
    }

    vector<pair<string, int>> similitud_arbol(nodo<string, vector<string>>* nodo, const vector<string>& sentimientos) {
        if (nodo == nullptr) {
            return {};
        }

        vector<pair<string, int>> cercanosIzq = similitud_arbol(nodo->left, sentimientos);
        vector<pair<string, int>> cercanosDer = similitud_arbol(nodo->right, sentimientos);

        vector<pair<string, int>> cercanosAct;

        // Obtener los sentimientos del libro actual
        const auto& datosLibro = nodo->value;

        for (const string& sentimiento : sentimientos) {
            

            // Compara cada sinónimo con los temas del libro actual
        
            for (const string& fSentimiento : datosLibro) {
                if (sentimiento == fSentimiento) {
                    nodo->matches++;
                    
                    // Agrega el libro y sale del bucle
                }
            }
            cercanosAct.push_back({nodo->key, nodo->matches});

        }


        cercanosAct.insert(cercanosAct.end(), cercanosIzq.begin(), cercanosIzq.end());
        cercanosAct.insert(cercanosAct.end(), cercanosDer.begin(), cercanosDer.end());
        
        return cercanosAct;
    }

public:

    
    avl() : root(nullptr) {}

    void insert(const Key& titulo, const Value& pSentimientos, const int apariciones) {
       root = insert(root, titulo, pSentimientos, apariciones);
    }

    void pMostrar() {
        mostrar(root);
    }

    nodo<Key, Value>* pBuscar(const Key& titulo) {
        return buscar(root, titulo);
    }


 

    vector<pair<string, int>> similitud(const avl* arbol, const vector<string>& sentimientos) {
        if (arbol == nullptr || arbol->root == nullptr) {
            return {};
        }

        return similitud_arbol(arbol->root, sentimientos);
    }

};
#endif
