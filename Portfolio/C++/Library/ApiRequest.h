#ifndef ApiRequest_
#define ApiRequest_ 
#include <iostream>
#include <string>
#include <curl/curl.h>
#include <vector>
#include <sstream>

#include "json.hpp"
#include "Sinonimo.h"

using json = nlohmann::json;
using namespace std;

class ApiRequest {
private:

   
    vector<string> topicsOrFeelings;
    vector<string> synonyms;
    Sinonimo sinonimos;
    string prompt;
    // Función de callback para manejar la respuesta de la solicitud HTTP
    static size_t WriteCallback(void* contents, size_t size, size_t nmemb, std::string* response) {
        size_t total_size = size * nmemb;
        response->append((char*)contents, total_size);
        return total_size;
    }

void parseResponse(string& response) {
    json jsonResponse = json::parse(response);

    if (jsonResponse.count("choices") > 0 && jsonResponse["choices"].is_array() && jsonResponse["choices"].size() > 0) {
        string assistantResponse = jsonResponse["choices"][0]["message"]["content"];

        istringstream iss(assistantResponse);
        string line;

        topicsOrFeelings.clear(); // Limpiar el vector de temas

        while (getline(iss, line)) {
            // Verificar si la línea comienza con un número (indicando un tema)
            if (line.empty() || !isdigit(line[0])) {
                continue;
            }

            // Encontrar el índice del primer espacio en blanco
            size_t firstSpace = line.find(' ');

            if (firstSpace != string::npos) {
                // Encontrar el índice de los dos puntos ":"
                size_t colonIndex = line.find(':');

                if (colonIndex != string::npos) {
                    // Agregar la palabra clave del tema (antes de los dos puntos) al vector
                    topicsOrFeelings.push_back(line.substr(firstSpace + 1, colonIndex - firstSpace - 1));
                }
            }
        }
    } else {
        topicsOrFeelings.clear();
    }
}


    void obtainFeelingsPhrase(string phraseOrWord, int tipo) {
        CURL* curl;
        CURLcode res;

        string url = "https://api.openai.com/v1/chat/completions";
        if (tipo == 1){
            prompt = "User: Give me the feelings of this phrase but first give me the central feeling'" + phraseOrWord + "'.";
        } else if(tipo == 2){
            prompt = "User: Give me 7 feelings presents in the book '" + phraseOrWord + "'.";
        }
        
        string api_key = "key";

        curl_global_init(CURL_GLOBAL_DEFAULT);
        curl = curl_easy_init();

        if (curl) {
            curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
            struct curl_slist* headers = NULL;
            headers = curl_slist_append(headers, ("Authorization: Bearer " + api_key).c_str());
            headers = curl_slist_append(headers, "Content-Type: application/json");
            curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

            json request_body = {
                {"messages", {
                    {{"role", "system"}, {"content", "You are a helpful assistant."}},
                    {{"role", "user"}, {"content", prompt}}
                }},
                {"model", "gpt-3.5-turbo"}
            };

            string json_data = request_body.dump();
            curl_easy_setopt(curl, CURLOPT_POSTFIELDS, json_data.c_str());

            string response_data;
            curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
            curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response_data);

            res = curl_easy_perform(curl);

            if (res != CURLE_OK) {
                fprintf(stderr, "Error en la solicitud: %s\n", curl_easy_strerror(res));
            } else {
                parseResponse(response_data);
            }

            curl_easy_cleanup(curl);
            curl_global_cleanup();
        }
    }

    

public:




    vector<string> getFeelings(string phraseOrWord, int tipo) {
        obtainFeelingsPhrase(phraseOrWord, tipo);
        return topicsOrFeelings;
    }

    Sinonimo ObtenerSinonimos(string word) {
    CURL *curl;
    CURLcode res;

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

            nlohmann::json response = nlohmann::json::parse(response_data);
            if (response.contains("typeOf")) {
                vector<string> synonyms = response["typeOf"].get<vector<string>>();
                sinonimos.synonyms[word] = synonyms;
            }
        }
    }

    return sinonimos;
}
    
};




#endif