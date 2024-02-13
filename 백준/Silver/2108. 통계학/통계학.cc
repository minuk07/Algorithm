#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

void average(int N, vector<int> vec){
    double sum = 0;
    for (int i=0; i<N; i++){
        sum += vec[i];
    }
    double aver = sum / N;
    int result = floor(aver + 0.5);
    //cout << aver << "\n";
    cout << result << "\n";
}

void mid(int N, vector<int> vec){
    cout << vec[N/2] << "\n";
}

int mode(int N, vector<int> vec){
    vector<int> count(8001);

    for(int i=0; i<N; i++){
        count[vec[i]+4000]++;
    }

    int max_idx = max_element(count.begin(), count.end()) - count.begin();
    for(int i=0; i<8001; i++){
        if(i != max_idx && count[i] == count[max_idx]){
            //cout << i - 4000 << "\n";
            return i-4000;
        }
    }
    return max_idx - 4000;

}

void boundary(int N, vector<int> vec){
    int answer = vec[N-1] - vec[0];
    cout << answer << "\n";
}

int main(){
    int N;
    cin >> N;
    vector<int> vec;

    for(int i=0; i<N; i++){
        int num;
        cin >> num;
        vec.push_back(num);
    }

    sort(vec.begin(), vec.end());
    
    average(N, vec);
    mid(N, vec);
    cout << mode(N, vec) << "\n";
    boundary(N, vec);
}