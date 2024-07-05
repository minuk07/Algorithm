#include <iostream>
#include <queue>

using namespace std;

int main(){
    int K, N;

    queue<int> q;

    cin >> N >> K;

    for (int i=1; i<N+1; i++){
        q.push(i);
    }

    cout << "<";

    while(!q.empty()){
        for(int i=0; i<K-1; i++){
            q.push(q.front());
            q.pop();
        }
        cout << q.front();

        if(q.size()>=2){
            cout << ", ";
        }
        q.pop();
    }

    cout << ">";
}