import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NzInputNumberModule } from 'ng-zorro-antd/input-number';
import { NzToolTipModule } from 'ng-zorro-antd/tooltip';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EvaluateSharedModule } from 'app/shared';
import { ExampleComponent, ExampleRoute } from './';
import { TooltipModule } from 'ngx-bootstrap';

const ENTITY_STATES = [...ExampleRoute];

@NgModule({
  imports: [
    EvaluateSharedModule,
    RouterModule.forChild(ENTITY_STATES),
    TooltipModule.forRoot(),
    CommonModule,
    NzInputNumberModule,
    NzToolTipModule
  ],
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
