import { DataSource } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { map } from 'rxjs/operators';
import { Observable, merge, BehaviorSubject } from 'rxjs';
import { Reservation } from '../../model/Reservation';

export class ReservationsDataSource extends DataSource<Reservation> {
  data = new BehaviorSubject<Reservation[]>([]);
  paginator: MatPaginator | undefined;
  sort: MatSort | undefined;

  constructor() {
    super();
  }

  connect(): Observable<Reservation[]> {
    if (this.paginator && this.sort) {
      return merge(this.data, this.paginator.page, this.sort.sortChange)
        .pipe(map(() => {
          return this.getPagedData(this.getSortedData([...this.data.value]));
        }));
    } else {
      throw Error('Please set the paginator and sort on the data source before connecting.');
    }
  }

  /**
   *  Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect(): void {}

  private getPagedData(data: Reservation[]): Reservation[] {
    if (this.paginator) {
      const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
      return data.splice(startIndex, this.paginator.pageSize);
    }
    return data;
  }

  private getSortedData(data: Reservation[]): Reservation[] {
    if (!this.sort || !this.sort.active || this.sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      const isAsc = this.sort?.direction === 'asc';
      switch (this.sort?.active) {
        case 'customer': return compare(a.customer.last_name, b.customer.last_name, isAsc);
        case 'place': return compare(a.place.location, b.place.location, isAsc);
        case 'start': return compare(new Date(a.reservation_start), new Date(b.reservation_start), isAsc);
        case 'end': return compare(new Date(a.reservation_end), new Date(b.reservation_end), isAsc);
        default: return 0;
      }
    });
  }

  updateData(newData: Reservation[]) {
    this.data.next(newData);
  }
}

function compare(a: string | number | Date, b: string | number | Date, isAsc: boolean): number {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
