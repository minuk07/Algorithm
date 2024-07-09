#include <iostream>
#include <vector>

using namespace std;

int M, N, K;
int ground[50][50] = {0};
//int visited[50][50] = {0};
int dy[4] = {1,-1,0,0};
int dx[4] = {0,0,1,-1};

void dfs(int y, int x){
    ground[y][x] = 0;
    
    for(int i=0; i<4; i++){
        int ddy = dy[i] + y;
        int ddx = dx[i] + x;

        if(ddy<0 || ddx<0 || ddy >= N || ddx >= M)
            continue;
        if(ground[ddy][ddx] == 1){
            //visited[y][x] = 1;
            dfs(ddy,ddx);
        }
    }

}

int main(){
    int T;
    cin >> T;

    for(int k=0;k<T;k++){
        cin >> M >> N >> K;

        int count = 0;
        ground[50][50] = {0};

        for(int i=0; i<K; i++){
            int X, Y;

            cin >> X >> Y;
            ground[Y][X] = 1;
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(ground[i][j] == 1){
                    //visited[i][j] = 1;
                    count ++;
                    dfs(i,j);
                }
            }
        }

        cout << count << "\n";
    }
}