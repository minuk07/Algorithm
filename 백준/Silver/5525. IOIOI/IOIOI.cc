#include <iostream>
#include <string>

using namespace std;
int N, M;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    cin >> M; 

    string S;
    cin >> S;

    int result = 0;
    for(int i=0; i<M; i++){

        int len = 0;
        if(S[i] == 'O') continue;

        if(S[i] == 'I'){
            while(S[i+1]=='O' && S[i+2]=='I'){
                len++;
                if(len == N){
                    result++;
                    len--;
                }
                i+=2;
            }
        }
    }

    cout << result << '\n';

    return 0;
}