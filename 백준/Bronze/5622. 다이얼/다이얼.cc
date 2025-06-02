#include <iostream>

using namespace std;

string s;

int number(char c){
    if (c == 'A' || c == 'B' || c == 'C') return 2;
    else if (c == 'D' || c == 'E' || c == 'F') return 3;
    else if (c == 'G' || c == 'H' || c == 'I') return 4;
    else if (c == 'J' || c == 'K' || c == 'L') return 5;
    else if (c == 'M' || c == 'N' || c == 'O') return 6;
    else if (c == 'P' || c == 'Q' || c == 'R' || c == 'S') return 7;
    else if (c == 'T' || c == 'U' || c == 'V') return 8;
    else if (c == 'X' || c == 'X' || c == 'Y' || c =='Z') return 9;
    else return 0;
}

int main(){

    int time = 0;

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> s;

    for(int i=0; i<s.length(); i++){
        if (number(s[i]) == 2) time += 3;
        else if (number(s[i]) == 3) time += 4;
        else if (number(s[i]) == 4) time += 5;
        else if (number(s[i]) == 5) time += 6;
        else if (number(s[i]) == 6) time += 7;
        else if (number(s[i]) == 7) time += 8;
        else if (number(s[i]) == 8) time += 9;
        else time += 10;
    }

    cout << time << '\n';
}