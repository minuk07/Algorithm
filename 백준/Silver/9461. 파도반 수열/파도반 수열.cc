#include <iostream>
#include <unordered_map>
#include <string>

using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int T;
    cin >> T;
    long long dp[101];
    dp[0]=0;
    dp[1]=dp[2]=dp[3]=1;
    for(int i=0; i<T; i++){
        int N;
        cin >> N;
        if(N < 4){
            cout << dp[N] << '\n';
        }
        else{
            for(int i=4; i<N+1; i++){
                dp[i] = dp[i-2] + dp[i-3];
            }
            cout << dp[N] << '\n';
        }
    }
    return 0;
}