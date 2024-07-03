#include <iostream>
#include <unordered_map>

using namespace std;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int N;
    cin >> N;
    int num;
    unordered_map<int,int> count;

    for(int i=0; i<N; i++){
        cin >> num;
        count[num]++;
    }

    int M;
    cin >> M;
    int num2;

    for(int j=0; j<M; j++){
        cin >> num2;
        cout << count[num2] << " ";
    }
}