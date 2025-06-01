#include <iostream>
#include <vector>

using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N;
    cin >> N;

    int fr = 0;

    vector<int> queue;

    for(int i=0; i<N; i++){
        string cmd;

        cin >> cmd;

        if(cmd == "push"){
            int num;
            cin >> num;
            queue.push_back(num);
        }
        else if(cmd == "pop"){
            if(queue.size() - fr == 0){
                cout << "-1" << '\n';
            }
            else{
                cout << queue[fr] << '\n';
                queue[fr] = 0;
                fr ++;
            }
        }
        else if(cmd == "size"){
            cout << queue.size() - fr << '\n';
        }
        else if(cmd == "empty"){
            if (queue.size() - fr == 0){
                cout << "1" << '\n';
            }
            else {
                cout << "0" << '\n';
            }
        }
        else if(cmd == "front"){
            if(queue.size() - fr == 0){
                cout << "-1" << '\n';
            }
            else{
                cout << queue[fr] << '\n';
            }
        }
        else {
            if(queue.size() - fr == 0){
                cout << "-1" << '\n';
            }
            else{
                cout << queue[queue.size()-1] << '\n';
            }
        }
    }

    return 0;
}