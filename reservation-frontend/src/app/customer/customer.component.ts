import { Component, inject } from '@angular/core';

import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';


@Component({
    selector: 'app-customer',
    templateUrl: './customer.component.html',
    styleUrl: './customer.component.css',
    imports: [
        MatInputModule,
        MatButtonModule,
        MatSelectModule,
        MatRadioModule,
        MatCardModule,
        ReactiveFormsModule
    ]
})

/**
 *     @Size(min = 1)
 *     @Column(name = "first_name")
 *     private String firstName;
 *     @Size(min = 1)
 *     @Column(name = "last_name")
 *     private String lastName;
 *     @Email
 *     private String email;
 *     @Password
 *     private String password;
 *     @Size(min = 3, max = 100)
 *     private String username;
 */

export class CustomerComponent {
  private fb = inject(FormBuilder);
  addressForm = this.fb.group({
    first_name: [null, Validators.required],
    last_name: [null, Validators.required],
    email: [null, Validators.compose([
      Validators.required, Validators.email])
    ],
    password: [null, Validators.compose([
        // TODO: further validation
        Validators.required, Validators.minLength(8), Validators.maxLength(128)
      ])
    ],
    username: [null, Validators.compose([
      Validators.required, Validators.minLength(3), Validators.maxLength(100)])
    ],
  });

  onSubmit(): void {
    console.log("Submitted!");
  }
}
