#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int N, M, V;

void dfs(int x, vector<int> graph[], bool visited[]){

    visited[x] = true;

    cout << x << " ";

    for(int i=0; i<graph[x].size(); i++){

        int y = graph[x][i];
        
        if (visited[y] == false){
            dfs(y, graph, visited);
        }
    }
}

void bfs(int x, vector<int> graph[], bool visited[]){
    queue<int> q;
    q.push(x);
    visited[x] = true;

    while(!q.empty()){
        int y = q.front();
        cout << y << " ";
        q.pop();

        for(int i=0; i<graph[y].size(); i++){
            int node = graph[y][i];
            if (visited[node] == false){
                q.push(node);
                visited[node] = true;
            }
        }
    }

}

int main(){
    cin >> N >> M >> V;

    vector<int> graph[N+1];
    bool visited[N+1] = {false};
    bool visited1[N+1] = {false};

    for(int i=0; i<M; i++){
        int num1, num2;
        cin >> num1 >> num2;
        graph[num1].push_back(num2);
        graph[num2].push_back(num1);
    }

    for(int i=0; i < N+1; i++){
        sort(graph[i].begin(), graph[i].end());
    }

    //bfs(V, graph, visited);
    dfs(V, graph, visited);
    
    cout << "\n";

    bfs(V, graph, visited1);

}