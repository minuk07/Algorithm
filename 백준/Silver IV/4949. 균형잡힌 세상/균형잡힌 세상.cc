#include <iostream>
#include <stack>
#include <string>
using namespace std;

int main(){
    string str;

    while(true){
        getline(cin, str);
        if (str == "."){
            return 0;
        }
        stack<char> stack;
        
        for(char ch : str){
            if (ch == '(' || ch == '['){
                stack.push(ch);
            }
            if (!stack.empty() &&ch == ')' && stack.top() == '(' ){
                stack.pop();
            }
            else if(!stack.empty() && ch == ']' && stack.top() == '['){
                stack.pop();
            }
            else if(stack.empty() && (ch == ']' || ch == ')')){
                stack.push(ch);
            }
            else if(!stack.empty() && ch == ']' && stack.top() != '['){
                stack.push(ch);
            }            
            else if(!stack.empty() && ch == ')' && stack.top() != '('){
                stack.push(ch);
            }
        }
        if(stack.empty()){
            cout << "yes" << "\n";
        }
        else {
            cout << "no" << "\n";
        }

    }
}