export class workers
{
    name : string;
    location : string;
    capacity : string;
    weight: string;
    holdingItems: [];
    actionHistory:[];
    // objToString (obj) {
    //     var str = '';
    //     for (var p in obj) {
    //       if (obj.hasOwnProperty(p)) {
    //         str += p + ':' + obj[p] + '\n';
    //       }
    //     }
    //     return str;
    //   }

    constructor(name, location, capacity,weight,holdingItems,actionHistory)

    {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.weight = weight;
        this.holdingItems = holdingItems;
        this.actionHistory = actionHistory;
    }
}