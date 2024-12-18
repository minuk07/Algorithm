#include <iostream>
#include <queue>

using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int n, w, L;
    cin >> n >> w >> L;
    queue<int> bridge;
    queue<int> q;
    int mintime = 0;
    int sum = 0;

    for(int i=0; i<n; i++){
        int num;
        cin >> num;
        q.push(num);
    }

    while(!q.empty()){
        if(bridge.size() == w){
            sum -= bridge.front();
            bridge.pop();
        }
        if(sum + q.front() <= L){
            bridge.push(q.front());
            sum += q.front();
            mintime++;
            q.pop();
        }
        else{
            bridge.push(0);
            mintime++;
        }
    }

    mintime += w;

    cout << mintime << '\n';

    return 0;
}