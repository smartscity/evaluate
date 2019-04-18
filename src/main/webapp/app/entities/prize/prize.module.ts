import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EvaluateSharedModule } from 'app/shared';
import { PrizeComponent, prizeRoute } from './';
import { TabsModule } from 'ngx-bootstrap';

const ENTITY_STATES = [...prizeRoute];

@NgModule({
  imports: [EvaluateSharedModule, RouterModule.forChild(ENTITY_STATES), TabsModule.forRoot()],
  declarations: [PrizeComponent],
  entryComponents: [PrizeComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EvaluatePrizeModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
