#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N,M;
int A[1000000];

int binary_search(int key){
    int start = 0, end = N - 1;

    while (start <= end){
        int mid = (start + end) / 2;

         if (A[mid]==key){
            return 1;
         }
         if( key > A[mid]){
            start = mid + 1;
         }
         else{
            end = mid - 1;
         }
    }
    return 0;

}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;

    for(int i=0; i< N; i++){
        cin >> A[i];
    }

    sort(A, A+N);

    cin >> M;
    
    int num;
    for(int i=0; i<M; i++){
        cin >> num;
        cout << binary_search(num) << "\n";
    }
    
    return 0;
}