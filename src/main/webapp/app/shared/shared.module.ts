import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { EvaluateSharedLibsModule, EvaluateSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [EvaluateSharedLibsModule, EvaluateSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [EvaluateSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EvaluateSharedModule {
  static forRoot() {
    return {
      ngModule: EvaluateSharedModule
    };
  }
}
