#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, K;
vector<bool> visited(100001, false);

void bfs(int start, int& cnt){
    queue<int> q;
    q.push(start);
    visited[start] = true;

    while(!q.empty()){
        int size = q.size();

        for(int i=0; i<size; i++){
            int x = q.front();
            q.pop();

            if(x == K){
                return;
            }
            if( x+1 < 100001 && visited[x+1] == false){
                q.push(x+1);
                visited[x+1] = true;
            }
            if( x-1 >= 0 && visited[x-1] == false){
                q.push(x-1);
                visited[x-1] = true;
            }
            if( x*2 < 100001 && visited[2*x] == false){
                q.push(2*x);
                visited[2*x] = true;
            }
        }
        cnt++;
    }
}

int main(){

    cin >> N >> K;

    int cnt = 0;

    bfs(N, cnt);
    
    cout << cnt << "\n";
}