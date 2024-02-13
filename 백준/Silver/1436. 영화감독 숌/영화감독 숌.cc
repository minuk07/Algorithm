#include <iostream>
#include <vector>
using namespace std;

int main(){
    int N;
    cin >> N;

    vector<int> number(10001);
    int i = 0;
    int j = 0;
    while(true){

        if(number[N-1] != 0){
            cout << number[N-1] << "\n";
            break;
        }

        int m = i;

        while(m>0){
            int n = m % 10;
            if(n == 6 && (m/10)%10 == 6 && ((m/10)/10%10) == 6){
                number[j] = i;
                j++;
                break;
            }
            else{
            m /= 10;
            }
        }
        i = i+1;
    }
}