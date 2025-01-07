#include <iostream>

using namespace std;

int n, m;
int arr[9] = {0,};
bool visited[9] = {false,};

void dfs(int num, int cnt){
    if(cnt == m){
        for(int i=0; i<m; i++){
            cout << arr[i] << ' ';
        }
        cout << '\n';
        return;
    }
    else{
        for(int i=num; i<=n; i++){
            if(!visited[i]){
                visited[i] = true;
                arr[cnt] = i;
                dfs(i+1, cnt+1);
                visited[i] = false;
            }
        }
    }
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    dfs(1,0);

    return 0;
}