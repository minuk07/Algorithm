#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

#define INF 1000000
int N, M;

void kevinBacon(vector<vector<int>>& dist, int N){
    for(int k=1; k<=N; k++){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if (dist[i][k] + dist[k][j] < dist[i][j]){
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }
    
    // for(int i=1; i<=N; i++){
    //     for(int j=1; j<=N; j++){
    //         cout << dist[i][j] << " ";
    //     }
    //     cout << "\n";
    // }
}

int main(){
    cin >> N >> M;
    vector<vector<int>> connection(N+1, vector<int>(N+1, INF));

    for(int i=0; i<N+1; i++){
        connection[i][i] = 0;
    }

    for(int i=0; i<M; i++){
        int num1, num2;
        cin >> num1 >> num2;

        connection[num1][num2] = 1;
        connection[num2][num1] = 1;
    }

    kevinBacon(connection, N);

    vector<int> v(N+1,0);

    v[0] = INF;

    for(int i=1; i<=N; i++){
        int sum = 0;
        for(int j=1; j<=N; j++){
            sum += connection[i][j];
        }
        v[i] = sum;
    }

    int result = 1;

    for(int i=2; i<=N; i++){
        if(v[i] < v[result] ){
            result = i;
        }
    }

    //cout << "kevinBacon array" << "\n";

    // for(int i=1; i<=N; i++){
    //     cout << v[i] << " ";
    // }

    // cout << "\n";

    cout << result << "\n";

}