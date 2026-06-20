#include <iostream>
#include <sstream>
#include <set>
#include <unordered_map>
#include <curl/curl.h>
#include <string>
#include <algorithm>

#include "json.hpp"

#include "sinonimo.h"

using json = nlohmann::json;
using namespace std;

size_t WriteCallback(void *contents, size_t size, size_t nmemb, string *output) {
    size_t total_size = size * nmemb;
    output->append(static_cast<char*>(contents), total_size);
    return total_size;
}

int main() {
    string input;
    cout << "Ingresa una frase: ";
    getline(cin, input);

    istringstream iss(input);
    set<string> uniqueWords;
    unordered_map<string, vector<string>> palabrasMap;

    CURL *curl;
    CURLcode res;

    // Tokenización de la frase
    string word;
    while (iss >> word) {
        // Elimina palabras repetidas
        if (uniqueWords.insert(word).second) {
            curl = curl_easy_init();
            if (curl) {
                string apiURL = "https://wordsapiv1.p.rapidapi.com/words/" + word + "/typeOf";
                curl_easy_setopt(curl, CURLOPT_URL, apiURL.c_str());

                struct curl_slist *headers = NULL;
                headers = curl_slist_append(headers, "X-RapidAPI-Key: 39d93563c7msh26be701ca499191p17f4c0jsnf251a0c8d21b");
                headers = curl_slist_append(headers, "X-RapidAPI-Host: wordsapiv1.p.rapidapi.com");
                curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

                string response_data;  // Respuesta para esta palabra específica

                curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
                curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response_data);

                res = curl_easy_perform(curl);
                curl_easy_cleanup(curl);

                if (res != CURLE_OK) {
                    cout << "Error en la solicitud HTTP para '" << word << "': " << curl_easy_strerror(res) << endl;
                } else {
                    cout << "Solicitud HTTP exitosa para '" << word << "'" << endl;

                    sinonimo palabra;
                    nlohmann::json response = nlohmann::json::parse(response_data);
                    
                    cout<< response_data<<endl;
                    if (response.contains("typeOf")) {
                       
                        vector<string> synonyms = response["typeOf"].get<vector<string>>();
                        palabrasMap[word] = synonyms;
                    }

                    
                }
            }
        }
    }

    // Imprime la caché de palabras importantes
    for (const auto& entry : palabrasMap) {
        cout << "Palabra: " << entry.first << "\n";
        cout << "Sinónimos:\n";
        for (const string& synonym : entry.second) {
            cout << "  " << synonym << endl;
        }
    }

    return 0;
}


// docker run -it --rm -v /c/Users/Lenovo/Documents/estruct2:/home gcc bash 
// apt update -y
// apt install curl   
//g++ -o programa_ejecutable main.cpp -lcurl
