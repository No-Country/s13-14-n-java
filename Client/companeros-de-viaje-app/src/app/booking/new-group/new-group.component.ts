import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-new-group',
  templateUrl: './new-group.component.html',
  styleUrls: ['./new-group.component.scss'],
})
export class NewGroupComponent implements OnInit {
  constructor() {}

  personal: any[] = [
    { name: 'Playas' },
    { name: 'Montañas' },
    { name: 'Rios' },
    { name: 'Eventos deportivos' },
    { name: 'Eventos musicales' },
    { name: 'turismo urbano' },
    { name: 'Turismo nocturno' },
  ];

  ngOnInit(): void {}
}
