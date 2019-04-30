import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EvaluationExampleService } from './example.service';
import { ExampleComponent } from './example.component';
import {Evaluation, IEvaluation} from 'app/shared/model/evaluation.model';
import {EvaluationService} from 'app/entities/evaluation/evaluation.service';

@Injectable({ providedIn: 'root' })
export class EvaluationResolve implements Resolve<IEvaluation> {
  constructor(private service: EvaluationExampleService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEvaluation> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Evaluation>) => response.ok),
        map((evaluation: HttpResponse<Evaluation>) => evaluation.body)
      );
    }
    return of(new Evaluation());
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
