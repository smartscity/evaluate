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
      },
      {
        path: 'speaker',
        loadChildren: './speaker/speaker.module#EvaluateSpeakerModule'
      },
      {
        path: 'speaker',
        loadChildren: './speaker/speaker.module#EvaluateSpeakerModule'
      },
      {
        path: 'evaluation',
        loadChildren: './evaluation/evaluation.module#EvaluateEvaluationModule'
      },
      {
        path: 'speaker',
        loadChildren: './speaker/speaker.module#EvaluateSpeakerModule'
      },
      {
        path: 'evaluation',
        loadChildren: './evaluation/evaluation.module#EvaluateEvaluationModule'
      },
      {
        path: 'speaker',
        loadChildren: './speaker/speaker.module#EvaluateSpeakerModule'
      },
      {
        path: 'evaluation',
        loadChildren: './evaluation/evaluation.module#EvaluateEvaluationModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EvaluateEntityModule {}
