import { LitElement, html, css, customElement } from 'lit-element';

@customElement('event-form')
export class EventForm extends LitElement {
  static get styles() {
    return css`
      :host {
          display: block;
          height: 100%;
      }
      `;
  }

  render() {
    return html`
      <vaadin-vertical-layout theme="spacing" style="width: 100%; height: 100%;">
        <vaadin-form-layout>
          <vaadin-text-field id="name" type="text" required tabindex="" label="Name"></vaadin-text-field>
        </vaadin-form-layout>
        <dynamic-action-component id="dynamicActionComponent" style="width: 80%;"></dynamic-action-component>
        <vaadin-horizontal-layout theme="spacing">
          <vaadin-button theme="primary" id="save">
            Save
          </vaadin-button>
          <vaadin-button theme="error" id="delete">
            Delete
          </vaadin-button>
          <vaadin-button theme="tertiary" id="close">
            Close
          </vaadin-button>
        </vaadin-horizontal-layout>
      </vaadin-vertical-layout>
    `;
  }
}
