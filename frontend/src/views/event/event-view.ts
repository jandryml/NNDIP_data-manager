import { LitElement, html, css, customElement } from 'lit-element';

@customElement('event-view')
export class EventView extends LitElement {
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
      <vaadin-vertical-layout style="padding: var(--lumo-space-m); width: 100%; height: 100%;" theme="spacing">
        <vaadin-horizontal-layout theme="spacing">
          <vaadin-text-field placeholder="Filter by name ..." type="text" tabindex="" id="filterText"></vaadin-text-field>
          <vaadin-button id="addEventButton">
            Add
          </vaadin-button>
        </vaadin-horizontal-layout>
        <vaadin-horizontal-layout theme="spacing" style="width: 100%; height: 100%;">
          <vaadin-grid style=" height: 100%;" tabindex="" is-attached id="grid"></vaadin-grid>
          <event-form style="width: 100%; height: 100%;" id="eventForm"></event-form>
        </vaadin-horizontal-layout>
      </vaadin-vertical-layout>
    `;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
