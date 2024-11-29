import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import { MatTableModule, MatTable } from '@angular/material/table';
import { MatPaginatorModule, MatPaginator } from '@angular/material/paginator';
import { MatSortModule, MatSort } from '@angular/material/sort';
import { ReservationsDataSource } from './reservations-datasource';
import { ReservationsService } from '../reservations.service';
import { Reservation } from '../model/Reservation';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrl: './reservations.component.css',
  standalone: true,
  imports: [MatTableModule, MatPaginatorModule, MatSortModule, DatePipe]
})
export class ReservationsComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<Reservation>;
  dataSource = new ReservationsDataSource();

  displayedColumns = ['customer', 'place', 'start', 'end'];

  constructor(private reservationsService: ReservationsService) {}

  ngOnInit() {
    this.loadReservations();
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }

  private loadReservations() {
    this.reservationsService.getReservations().subscribe({
      next: (reservations) => {
        console.log("fetched reservations: ", reservations)
        this.dataSource.updateData(reservations);
      },
      error: (error) => {
        console.error('Error loading reservations:', error);
      }
    });
  }
}
