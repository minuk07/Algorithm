#include <iostream>
#include <cmath>

using namespace std;

int N, r, c;
int result;

void dnc(int size, int y, int x){
    if (y==r && x==c){
        cout << result << "\n";
        return;
    }

    if (r < y + size && r >= y && c < x + size && c >= x){

        dnc(size/2, y, x);

        dnc(size/2, y, x + size/2);

        dnc(size/2 , y+size/2, x);

        dnc(size/2, y+size/2, x+size/2);
    }
    else{
        result += size*size;
    }
}

int main(){
    cin >> N >> r >> c;

    int size = pow(2,N);

    dnc(size, 0, 0);

}