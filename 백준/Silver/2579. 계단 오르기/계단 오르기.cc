#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(){
    int N;
    cin >> N;
    
    int scores[301];
    int myscores[301];

    for(int i=1; i<N+1; i++){
        int num;
        cin >> num;
        scores[i] = num;
    }

    myscores[1] = scores[1];
    myscores[2] = scores[2] + scores[1];
    myscores[3] = max(scores[1] + scores[3], scores[2] + scores[3]);
    
    for(int i=4; i<N+1; i++){
        myscores[i] = max(myscores[i-2] + scores[i], myscores[i-3] + scores[i-1] + scores[i]);
    }

    cout << myscores[N] << "\n";
}