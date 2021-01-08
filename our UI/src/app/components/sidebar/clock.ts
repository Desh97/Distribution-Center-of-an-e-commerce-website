import { Time } from '@angular/common';

export class clock
{
    step : object;
    
  /*  objToString (obj) {
        var str = '';
        for (var p in obj) {
          if (obj.hasOwnProperty(p)) {
            str += p + ':' + obj[p] + '\n';
          }
        }
        // console.log("inside function")
        return str;
      }*/

    constructor(step)

    {
        this.step= step;
        
    }
}