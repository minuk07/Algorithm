#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, m;
vector<int> arr;
int res[9] = {0};
bool visited[9] = {false};

void dfs(int cnt) {
    if (cnt == m) {
        for (int i = 0; i < m; i++) {
            cout << res[i] << ' ';
        }
        cout << "\n";
        return;
    }
    for (int i = 0; i < n; i++) {
        if (!visited[i]) { // 이미 사용된 숫자는 제외
            visited[i] = true; // 숫자 선택
            res[cnt] = arr[i]; // 현재 선택한 숫자를 결과 배열에 추가
            dfs(cnt + 1); // 다음 자리 탐색
            visited[i] = false; 
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        int num;
        cin >> num;
        arr.push_back(num);
    }

    sort(arr.begin(), arr.end()); // 사전순 출력을 위해 정렬

    dfs(0);

    return 0;
}