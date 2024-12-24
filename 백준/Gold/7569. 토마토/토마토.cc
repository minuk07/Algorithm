#include <iostream>
#include <queue>
#include <utility>
using namespace std;

int box[102][102][102];

int dy[6] = {1,-1,0,0,0,0};
int dx[6] = {0,0,-1,1,0,0};
int dz[6] = {0,0,0,0,1,-1};
int dist[102][102][102];

queue<pair<pair<int,int>,int> > q;

int M, N , H;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> M >> N >> H;

    for(int i=0; i<H; i++){
        for(int j=0; j<N; j++){
            for(int k=0; k<M; k++){
                int num;
                cin >> num;
                box[i][j][k] = num;
                if(num == 1){
                    q.push(make_pair(make_pair(i, j), k));                }
                if(num == 0){
                    dist[i][j][k] = -1;
                }
            }
        }
    }

    while(!q.empty()){
        pair<pair<int,int>,int> cur = q.front();
        q.pop();
        for(int i=0; i<6; i++){
            int uz = cur.first.first + dz[i];
            int ux = cur.first.second + dx[i];
            int uy = cur.second + dy[i];

            if(uy < 0 || ux < 0 || uz < 0 || uy >= M || ux >= N || uz >= H ) continue;
            if(dist[uz][ux][uy] >= 0) continue;
            dist[uz][ux][uy] = dist[cur.first.first][cur.first.second][cur.second] + 1;
            q.push(make_pair(make_pair(uz,ux),uy));
        }
    }

    int result = 0;

    for(int i=0; i<H; i++){
        for(int j=0; j<N; j++){
            for(int k=0; k<M; k++){
                if (dist[i][j][k] == -1){
                    cout << "-1" << '\n';
                    return 0;
                }
                result = max(result, dist[i][j][k]);
            }
        }
    }

    cout << result << '\n';

    return 0;
}