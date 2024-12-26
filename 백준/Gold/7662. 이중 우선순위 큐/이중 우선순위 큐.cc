#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
#include <map>

using namespace std;

priority_queue<int, vector<int>, less<int> > maxpq;
priority_queue<int, vector<int>, greater<int> > minpq;
map<int,int> m;

void cleanpq(){
    while(!maxpq.empty() && m[maxpq.top()] == 0){
        maxpq.pop();
    }
    while(!minpq.empty() && m[minpq.top()] == 0){
        minpq.pop();
    }
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N;
    cin >> N;

    for(int i=0; i<N; i++){
        int T;
        cin >> T;

        while(!maxpq.empty()) {maxpq.pop();}
        while(!minpq.empty()) {minpq.pop();}
        m.clear();

        for(int j=0; j<T; j++){
            int num;
            char ch;
            cin >> ch >> num;
            if(ch == 'I'){
                maxpq.push(num);
                minpq.push(num);
                m[num]++;
            }
            else if(ch == 'D'){
                if (num == 1){
                    if(!maxpq.empty()){
                        m[maxpq.top()]--;
                        maxpq.pop();
                    }
                }
                else {
                    if(!minpq.empty()){
                        m[minpq.top()]--;
                        minpq.pop();
                    }
                }
                cleanpq();
            }
        }
        cleanpq();
        if(maxpq.empty() || minpq.empty()) cout << "EMPTY" << '\n';
        else{
            cout << maxpq.top() << " " << minpq.top() << '\n';
        }
    }

    return 0;
}