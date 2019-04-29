import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Speaker } from 'app/shared/model/speaker.model';
import { SpeakerService } from './example.service';
import { ExampleComponent } from './example.component';
import { ISpeaker } from 'app/shared/model/speaker.model';

@Injectable({ providedIn: 'root' })
export class SpeakerResolve implements Resolve<ISpeaker> {
  constructor(private service: SpeakerService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISpeaker> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Speaker>) => response.ok),
        map((speaker: HttpResponse<Speaker>) => speaker.body)
      );
    }
    return of(new Speaker());
  }
}

export const ExampleRoute: Routes = [
  {
    path: '',
    component: ExampleComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'global.menu.entities.speaker'
    },
    canActivate: [UserRouteAccessService]
  }
];
