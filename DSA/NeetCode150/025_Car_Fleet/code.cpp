
#include <vector>
#include <algorithm>
using namespace std;
int carFleet(int target, vector<int>& position, vector<int>& speed){
    vector<pair<int,double>> cars;
    for(size_t i=0;i<position.size();++i) cars.push_back({position[i], (double)(target-position[i])/speed[i]});
    sort(cars.begin(), cars.end(), [](auto &a, auto &b){return a.first<b.first;});
    int fleets=0; double cur=0;
    for(int i=(int)cars.size()-1;i>=0;--i){
        if(cars[i].second>cur){ ++fleets; cur=cars[i].second; }
    }
    return fleets;
}
