#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<pair<int, int>> cnt(41, make_pair(0,0));

void fibonaccicount(int N){

    for(int i=2; i<=N; i++){
        if(cnt[i] == make_pair(0,0)){
            cnt[i] = make_pair(
                cnt[i - 1].first + cnt[i - 2].first,
                cnt[i - 1].second + cnt[i - 2].second
            );
        }
    }
}

int main(){
    
    cnt[0] = make_pair(1,0);
    cnt[1] = make_pair(0,1);

    int T;
    cin >> T;

    vector<int> vec;

    for(int i=0; i<T; i++){
        int N;
        cin >> N;
        fibonaccicount(N);
        cout << cnt[N].first << " " << cnt[N].second << "\n";
    }
}
