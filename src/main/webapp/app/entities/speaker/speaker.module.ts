import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { EvaluateSharedModule } from 'app/shared';
import {
  SpeakerComponent,
  SpeakerDetailComponent,
  SpeakerUpdateComponent,
  SpeakerDeletePopupComponent,
  SpeakerDeleteDialogComponent,
  speakerRoute,
  speakerPopupRoute
} from './';

const ENTITY_STATES = [...speakerRoute, ...speakerPopupRoute];

@NgModule({
  imports: [EvaluateSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SpeakerComponent,
    SpeakerDetailComponent,
    SpeakerUpdateComponent,
    SpeakerDeleteDialogComponent,
    SpeakerDeletePopupComponent
  ],
  entryComponents: [SpeakerComponent, SpeakerUpdateComponent, SpeakerDeleteDialogComponent, SpeakerDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EvaluateSpeakerModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
