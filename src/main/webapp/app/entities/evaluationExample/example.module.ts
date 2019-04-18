import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EvaluateSharedModule } from 'app/shared';
import { ExampleComponent, ExampleRoute } from './';

const ENTITY_STATES = [...ExampleRoute];

@NgModule({
  imports: [EvaluateSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [ExampleComponent],
  entryComponents: [ExampleComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EvaluateExampleModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
