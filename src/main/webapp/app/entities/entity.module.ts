import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'speaker',
        loadChildren: './speaker/speaker.module#EvaluateSpeakerModule'
      },
      {
        path: 'evaluation',
        loadChildren: './evaluation/evaluation.module#EvaluateEvaluationModule'
      },
      {
        path: 'prize',
        loadChildren: './prize/prize.module#EvaluatePrizeModule'
      },
      {
        path: 'evaluationExample',
        loadChildren: './evaluationExample/example.module#EvaluateExampleModule'
      }
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EvaluateEntityModule {}
