#include <iostream>
#include <string>

using namespace std;

int main(){
    string str;
    cin >> str;

    string num = "";
    int result = 0;
    bool flag = false;

    for(int i=0; i <= str.length(); i++){
        
        if (str[i] == '+' || str[i] == '-' || str[i] == '\0') {	
            if(flag == true){
                result -= stoi(num);
                num = "";
            }
            else{
                result += stoi(num);
                num = "";
            }
        }
        else {
            num += str[i];
        }

        if(str[i] == '-'){
                flag = true;
        }
    }

    cout << result <<"\n";
}