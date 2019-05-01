import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { ISpeaker, Speaker } from 'app/shared/model/speaker.model';
import { SpeakerService } from './speaker.service';

@Component({
  selector: 'jhi-speaker-update',
  templateUrl: './speaker-update.component.html'
})
export class SpeakerUpdateComponent implements OnInit {
  speaker: ISpeaker;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    title: [],
    orgName: [],
    actor: [],
    speaker: [],
    level: [],
    pdf: [],
    pdfContentType: [],
    path: [],
    review: [],
    remark: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected speakerService: SpeakerService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ speaker }) => {
      this.updateForm(speaker);
      this.speaker = speaker;
    });
  }

  updateForm(speaker: ISpeaker) {
    this.editForm.patchValue({
      id: speaker.id,
      title: speaker.title,
      orgName: speaker.orgName,
      actor: speaker.actor,
      speaker: speaker.speaker,
      level: speaker.level,
      pdf: speaker.pdf,
      pdfContentType: speaker.pdfContentType,
      path: speaker.path,
      review: speaker.review,
      remark: speaker.remark
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    alert(field);
    alert(field.path);
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const speaker = this.createFromForm();
    if (speaker.id !== undefined) {
      this.subscribeToSaveResponse(this.speakerService.update(speaker));
    } else {
      this.subscribeToSaveResponse(this.speakerService.create(speaker));
    }
  }

  private createFromForm(): ISpeaker {
    const entity = {
      ...new Speaker(),
      id: this.editForm.get(['id']).value,
      title: this.editForm.get(['title']).value,
      orgName: this.editForm.get(['orgName']).value,
      actor: this.editForm.get(['actor']).value,
      speaker: this.editForm.get(['speaker']).value,
      level: this.editForm.get(['level']).value,
      pdfContentType: this.editForm.get(['pdfContentType']).value,
      pdf: this.editForm.get(['pdf']).value,
      path: this.editForm.get(['path']).value,
      review: this.editForm.get(['review']).value,
      remark: this.editForm.get(['remark']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISpeaker>>) {
    result.subscribe((res: HttpResponse<ISpeaker>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
