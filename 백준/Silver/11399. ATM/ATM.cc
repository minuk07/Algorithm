#include <iostream>
#include <algorithm>

using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int N;
    cin >> N;
    int P[N];

    for (int i=0; i<N; i++){
        int m;
        cin >> m;
        P[i] = m;
    }
    sort(P,P+N);
    int dp[N];
    int minminute = 0;
    int finmin = 0;
    for(int i=0; i<N; i++){
        minminute += P[i];
        dp[i] = minminute;
        finmin += dp[i];
    }


    cout << finmin << '\n';
    return 0;
}