#include <iostream>
#include <vector>

using namespace std;

void dfs(const vector<vector<int>>& graph, bool visited[], int start, int& count){
    visited[start] = true;

    for(int i = 0; i < graph[start].size(); i++){
        int y = graph[start][i];
        if(!visited[y]){
            count++;
            dfs(graph, visited, y, count);
        }
    }
}

int main(){
    int N, num;
    cin >> N >> num;

    vector<vector<int>> graph(N + 1);  // 크기를 동적으로 초기화
    bool visited[N + 1] = {false};  // 초기화

    for(int i = 0; i < num; i++){
        int net1, net2;
        cin >> net1 >> net2;
        graph[net1].push_back(net2);
        graph[net2].push_back(net1);  // 양방향 그래프
    }

    int count = 0;
    dfs(graph, visited, 1, count);

    cout << count << "\n";

    return 0;
}
