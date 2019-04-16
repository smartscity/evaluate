import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
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
    pdf: []
  });

  constructor(protected speakerService: SpeakerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

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
      pdf: speaker.pdf
    });
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
      pdf: this.editForm.get(['pdf']).value
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
}
