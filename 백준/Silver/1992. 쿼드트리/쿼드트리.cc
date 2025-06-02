#include <iostream>

using namespace std;

string arr;
int N;
int qtree[64][64];

void dnq(int y, int x, int size){
    for(int i=y; i<y+size; i++){
        for(int j=x; j<x+size; j++){
            if(qtree[i][j] != qtree[y][x]){
                cout << "(";
                dnq(y,x,size/2);
                dnq(y,x + size/2,size/2);
                dnq(y + size/2,x,size/2);
                dnq(y + size/2, x + size/2, size/2);
                cout << ")";
                return;
            }
        }
    }
    cout << qtree[y][x];
}


int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    for(int i=0; i<N; i++){
        cin >> arr;
        for(int j=0; j<N; j++){
            qtree[i][j] = arr[j] - '0';
        }
    }

    dnq(0,0,N);

}