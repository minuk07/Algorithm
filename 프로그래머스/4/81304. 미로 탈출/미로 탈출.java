import java.util.*;

class Solution {
    
    static final int INF = Integer.MAX_VALUE;
    
    static int n, start, end;
    static int[][] roads;
    static int[] traps;
    
    static List<Edge>[] graph;
    static int[] trapIdx;
    
    static int stateCnt;
    
    static class Edge{
        int dest, take;
        boolean origin;
        
        Edge(int dest, int take, boolean origin){
            this.dest = dest; this.take = take; this.origin = origin;
        }
    }
    
    static class Node{
        
        int room, take, state;
        
        Node(int room, int take, int state){
            this.room = room; this.take = take; this.state = state;
        }
    }
    
    static boolean isActive(int room, int state){
        
        if(trapIdx[room] == -1){
            return false;
        }
        
        int idx = trapIdx[room];
        
        return (state & (1 << idx)) != 0;
    }
    
    static int dijkstra(int start){
        
        int[][] dist = new int[n+1][stateCnt];;
        
        for(int i=0; i<=n; i++){
            Arrays.fill(dist[i], INF);
        }
        
        dist[start][0] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.take - b.take);
        pq.add(new Node(start, 0, 0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            int curRoom = cur.room; int curState = cur.state; int curTake = cur.take;
            if(curTake > dist[curRoom][curState]){
                continue;
            }
            
            if(curRoom == end){
                return dist[end][curState];
            }
            
            for(Edge next : graph[curRoom]){
                int nextRoom = next.dest;
                
                boolean curActive = isActive(curRoom, curState);
                boolean nextActive = isActive(nextRoom, curState);

                boolean isReverse = curActive ^ nextActive;
                
                if(isReverse == next.origin){
                    continue;
                }
                
                int nextState = curState;
                
                if(trapIdx[nextRoom] != -1){
                    nextState ^= (1 << trapIdx[nextRoom]);
                }
                
                int newTake = curTake + next.take;
                
                if(newTake < dist[nextRoom][nextState]){
                    dist[nextRoom][nextState] = newTake;
                    pq.add(new Node(nextRoom, newTake, nextState));
                }
            }
        }
        
        return -1;
    }
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        
        this.n = n; this.start = start; this.end = end;
        this.roads = roads; this.traps = traps;
        
        graph = new ArrayList[n+1];
        trapIdx = new int[n+1];
        stateCnt = 1 << traps.length;
        
        Arrays.fill(trapIdx, -1);
        
        for(int i=0; i<traps.length; i++){
            trapIdx[traps[i]] = i;
        }
        
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] road : roads){
            
            int a = road[0];
            int b = road[1];
            int take = road[2];
            
            graph[a].add(new Edge(b, take, true));
            graph[b].add(new Edge(a, take, false));
        }
        
        
        
        return dijkstra(start);
    }
}