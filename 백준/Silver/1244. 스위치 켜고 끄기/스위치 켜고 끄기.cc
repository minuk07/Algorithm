#include <iostream>

using namespace std;

int N;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    int arr[N+1];

    for(int i=1; i<=N; i++){
        int num;
        cin >> num;
        arr[i] = num;
    }

    int stdnum;
    cin >> stdnum;

    for(int i=0; i<stdnum; i++){
        int gen, swnum;
        cin >> gen >> swnum;

        if(gen == 1){
            for(int j=swnum; j<=N; j+=swnum){
                arr[j] = !arr[j];
            }
        }
        else{
            arr[swnum] = !arr[swnum];
            int t = 1;
            while(swnum-t>0 && swnum+t<=N){
                if(arr[swnum-t] != arr[swnum+t]) break;
                arr[swnum-t] = !arr[swnum-t];
                arr[swnum+t] = !arr[swnum+t];
                t++;
            } 
        }
    }

    for(int i=1; i<=N; i++){
        cout << arr[i] << ' ';
        if( i%20 == 0) cout << '\n';
    }
}